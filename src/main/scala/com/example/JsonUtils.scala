package com.example

import play.api.libs.json._
import scala.util.{Failure, Success, Try}

object JsonUtils {

  def parse(input: String): JsResult[JsValue] = {
    Try(Json.parse(input)) match {
      case Success(success) => JsSuccess(success)
      case Failure(failure) => JsError(failure.getMessage)
    }
  }
  def parseAsOpt[A](input: String)(implicit tjs: play.api.libs.json.Reads[A]): Option[A] = {
    parse(input) match {
      case JsSuccess(jsValue, _) => {
        jsValue.asOpt[A]
      }
      case JsError(err) => None
    }

  }

}
