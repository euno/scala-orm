package example.scalikejdbc.models

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class ProductSpec extends Specification {

  "Product" should {

    val p = Product.syntax("p")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Product.find(123)
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Product.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Product.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = Product.findAllBy(sqls.eq(p.productId, 123))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Product.countBy(sqls.eq(p.productId, 123))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Product.create(productId = 123, categoryId = 123, productName = "MyString", productType = 123)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Product.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Product.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Product.findAll().head
      Product.destroy(entity)
      val shouldBeNone = Product.find(123)
      shouldBeNone.isDefined should beFalse
    }
  }

}
        