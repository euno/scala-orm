package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class HistorySpec extends Specification {

  "History" should {

    val h = History.syntax("h")

    "find by primary keys" in new AutoRollback {
      val maybeFound = History.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = History.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = History.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = History.findAllBy(sqls.eq(h.historyId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = History.countBy(sqls.eq(h.historyId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = History.create(productId = 123, quantity = 123, amount = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = History.findAll().head
      // TODO modify something
      val modified = entity
      val updated = History.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = History.findAll().head
      History.destroy(entity)
      val shouldBeNone = History.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        