package pelagic.cache

import scala.collection.mutable.HashMap
import java.util.{LinkedHashMap, Map => JavaMap}


class Cache[K,V](theMaxSize: Int) { 
  
  val maxSize = theMaxSize

  var cache = new LinkedHashMap[K, V] {
    override def removeEldestEntry(entry: JavaMap.Entry[K, V]) = {
      size > maxSize
    }
  }

  def update(key: K, value: V) = {
    cache.put(key, value)
  }

  def get(key: K): Option[V] = {
    cache.get(key) match {
      case null => None 
      case v => Some(v)
    }
  }
}
