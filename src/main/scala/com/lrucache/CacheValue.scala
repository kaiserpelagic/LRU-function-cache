package com.lrucache

import net.liftweb.util.Helpers
  
case class CacheValue[T](compute: () => T, lifespan: Long) {
  var value: Option[T] = None
  var lastComputed: Long = 0

  def get = {
    val now = Helpers.millis
    if (lifespan < now - lastComputed) {
      value = None
    }
    value match {
      case Some(v) => v
      case _ => {
        val ret = compute()
        lastComputed = now
        value = Some(ret)
        ret
      }
    }
  }
}
