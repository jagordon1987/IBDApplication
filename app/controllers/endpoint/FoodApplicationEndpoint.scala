package controllers.endpoint

import javax.inject.Inject


import persistence.Food
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.{Controller, Action}
import service.FoodService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class CreateFood(name: String, additionalInformation: String)
class FoodApplicationEndpoint @Inject()(foodService: FoodService) extends Controller  {
  implicit val writesFood = Json.writes[Food]
  implicit val readsFood = Json.reads[CreateFood]

  def getAllFoods = Action.async { implicit request =>
    foodService.getAllFoods map(foods => Ok(Json.toJson(foods)))
  }

  def deleteFood(id: Long) = Action.async { implicit request =>
    foodService.deleteFood(id) map { food =>
      Ok("Food " + food + " deleted")
    }
  }

  def getFood(id: Long) = Action.async { implicit request =>
    foodService.getFood(id) map(food => Ok(Json.toJson(food)))
  }

  def addNewFood() = Action.async(parse.json) { implicit request =>
    request.body.validate[CreateFood] match {
      case JsSuccess(createFood, _) =>
        val food = new Food(0, createFood.name, createFood.additionalInformation)
        foodService.addNewFood(food) map { aFood =>
          Ok(Json.toJson(aFood))
        }
      case JsError(error) => Future {
        BadRequest
      }
    }
  }

  def updateFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationEndpoint - Update food called")
  }
}
