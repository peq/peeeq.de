import play.api._
import play.api.mvc._
import play.filters.csrf._
import play.filters.gzip.Gzip
import play.filters.gzip.GzipFilter

object Global extends WithFilters(CSRFFilter(),new GzipFilter()) with GlobalSettings {
  // ... onStart, onStop etc
}