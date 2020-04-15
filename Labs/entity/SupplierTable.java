/* Created by Vince Chang */

package entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SupplierTable {

  private static Map<UUID, Supplier> data = new HashMap<UUID, Supplier>();

  static {
    for (Supplier x : new Supplier[]{
      new Supplier(new UUID(0, 1), "Floor Mart"),
      new Supplier(new UUID(0, 2), "Bullseye"),
      new Supplier(new UUID(0, 3), "Garden Depot"),
      new Supplier(new UUID(0, 4), "SCCS")
    }) {
      data.put(x.getId(), x);
    }
  }

  public static List<Supplier> getAll() {
    List<Supplier> rv = new LinkedList<Supplier>();
    for (Map.Entry<UUID, Supplier> e : data.entrySet()) {
      rv.add(e.getValue());
    }
    return rv;
  }

  public static Supplier findByPrimaryKey(UUID pk) {
    return data.get(pk);
  }
}