package controllers.application

import javax.inject.Inject
import persistence.Food
import play.api.mvc._
import service.FoodService
import util.forms.FoodForm
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

class FoodApplicationController @Inject()(foodService: FoodService) extends Controller {

  def getAllFoods = Action.async { implicit request =>
    foodService.getAllFoods map { foods =>
      Ok(views.html.foods(foods))
    }
  }

  def deleteFood(id: Long) = Action.async { implicit request =>
    foodService.deleteFood(id) map { food =>
      Redirect(routes.FoodApplicationController.getAllFoods())
    }
  }

  def getFood(id: Long) = Action.async  { implicit request =>
    foodService.getFood(id) map {
        case Some(x) => Ok(views.html.food(x))
        case None => BadRequest("Bad request")
    }
  }

  val foodForm = Action {
    Ok(views.html.foodForm(FoodForm.form))
  }


  def addNewFood() = Action.async { implicit request =>
    FoodForm.form.bindFromRequest.fold(
      errorForm => Future.successful(Ok(views.html.foodForm(errorForm))),
      data => {
        val food = Food(0, data.name, data.additionalInformation)
        foodService.addNewFood(food).map(res =>
          Redirect(routes.FoodApplicationController.getAllFoods())
        )
      })
  }

  def updateFood(id: Long) = Action { implicit request =>
    Ok("FoodApplicationController - Update food called")
  }

}
