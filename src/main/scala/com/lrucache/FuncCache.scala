package com.lrucache

object FuncCache {
  val DEFAULT_LIFESPAN = 600000   // 10 min
  
  val cache = new Cache[String, CacheValue[_]](2500)

  def update[T](key: String, compute: () => T): T = {
    val cValue = CacheValue(compute, DEFAULT_LIFESPAN)
    cache.update(key, cValue)
    cValue.get 
  }

  def getOrElseUpdate[T](key: String, compute: () => T): T = {
    cache.get(key) match {
      case None => update(key, compute)
      case Some(v) => v.asInstanceOf[T]
    }
  }
}
