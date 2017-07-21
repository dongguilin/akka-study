package com.guilin.akka.study.hello

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import language.postfixOps
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by guilin on 2017/7/21.
  */
case object Ping

case object Pong

class Pinger extends Actor {

  var countDown = 100

  override def receive={
    case Pong =>
      println(s"${self.path} received pong, count down $countDown")
      if (countDown > 0) {
        countDown -= 1
        sender() ! Ping
      } else {
        sender() ! PoisonPill
        self ! PoisonPill
      }
  }
}

class Ponger(pinger: ActorRef) extends Actor {
  override def receive = {
    case Ping =>
      println(s"${self.path} received ping")
      pinger ! Pong
  }
}

object PingPong{
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("pingpong")

    val pinger = system.actorOf(Props[Pinger], "pinger")

    val ponger = system.actorOf(Props(classOf[Ponger], pinger), "ponger")
    system.scheduler.scheduleOnce(500 millis) {
      ponger ! Ping
    }
  }
}


