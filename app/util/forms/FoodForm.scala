package util.forms

import play.api.data.Form
import play.api.data.Forms._

case class FoodFormData(name: String, additionalInformation: String)

object FoodForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "additionalInformation" -> nonEmptyText
    )(FoodFormData.apply)(FoodFormData.unapply)
  )
}
