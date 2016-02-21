package controllers.endpoint

import play.api.mvc._

class FoodApplicationEndpoint extends Controller {
  def index = Action {
    Ok(views.html.index("Test to make sure application is working"))
  }
}
