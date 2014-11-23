package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class RelProductProductTagSpec extends Specification {

  "RelProductProductTag" should {

    val rppt = RelProductProductTag.syntax("rppt")

    "find by primary keys" in new AutoRollback {
      val maybeFound = RelProductProductTag.find(123, 123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = RelProductProductTag.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = RelProductProductTag.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = RelProductProductTag.findAllBy(sqls.eq(rppt.productId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = RelProductProductTag.countBy(sqls.eq(rppt.productId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = RelProductProductTag.create(productId = 123, productTagId = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = RelProductProductTag.findAll().head
      // TODO modify something
      val modified = entity
      val updated = RelProductProductTag.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = RelProductProductTag.findAll().head
      RelProductProductTag.destroy(entity)
      val shouldBeNone = RelProductProductTag.find(123, 123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        