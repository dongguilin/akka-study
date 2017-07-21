package com.guilin.akka.study.hello

import akka.actor.Actor
import akka.event.Logging

/**
  * Created by guilin on 2017/7/21.
  */
class MyActor extends Actor {

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case "test" => log.info("received test")
    case _ => log.info("received unknown message")
  }
}