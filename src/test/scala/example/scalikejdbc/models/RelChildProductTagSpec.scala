package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class RelChildProductTagSpec extends Specification {

  "RelChildProductTag" should {

    val rcpt = RelChildProductTag.syntax("rcpt")

    "find by primary keys" in new AutoRollback {
      val maybeFound = RelChildProductTag.find(123, 123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = RelChildProductTag.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = RelChildProductTag.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = RelChildProductTag.findAllBy(sqls.eq(rcpt.productId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = RelChildProductTag.countBy(sqls.eq(rcpt.productId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = RelChildProductTag.create(productId = 123, productTagId = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = RelChildProductTag.findAll().head
      // TODO modify something
      val modified = entity
      val updated = RelChildProductTag.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = RelChildProductTag.findAll().head
      RelChildProductTag.destroy(entity)
      val shouldBeNone = RelChildProductTag.find(123, 123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        