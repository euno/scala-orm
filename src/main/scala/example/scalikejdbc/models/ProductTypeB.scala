package example.scalikejdbc.models

import scalikejdbc._

case class ProductTypeB(
  productId: Int, 
  bCount: Int) {

  def save()(implicit session: DBSession = ProductTypeB.autoSession): ProductTypeB = ProductTypeB.save(this)(session)

  def destroy()(implicit session: DBSession = ProductTypeB.autoSession): Unit = ProductTypeB.destroy(this)(session)

}
      

object ProductTypeB extends SQLSyntaxSupport[ProductTypeB] {

  override val schemaName = Some("orm_test")

  override val tableName = "product_type_b"

  override val columns = Seq("product_id", "b_count")

  def apply(ptb: SyntaxProvider[ProductTypeB])(rs: WrappedResultSet): ProductTypeB = apply(ptb.resultName)(rs)
  def apply(ptb: ResultName[ProductTypeB])(rs: WrappedResultSet): ProductTypeB = new ProductTypeB(
    productId = rs.get(ptb.productId),
    bCount = rs.get(ptb.bCount)
  )
      
  val ptb = ProductTypeB.syntax("ptb")

  override val autoSession = AutoSession

  def find(productId: Int)(implicit session: DBSession = autoSession): Option[ProductTypeB] = {
    withSQL {
      select.from(ProductTypeB as ptb).where.eq(ptb.productId, productId)
    }.map(ProductTypeB(ptb.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[ProductTypeB] = {
    withSQL(select.from(ProductTypeB as ptb)).map(ProductTypeB(ptb.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(ProductTypeB as ptb)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[ProductTypeB] = {
    withSQL { 
      select.from(ProductTypeB as ptb).where.append(sqls"${where}")
    }.map(ProductTypeB(ptb.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(ProductTypeB as ptb).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productId: Int,
    bCount: Int)(implicit session: DBSession = autoSession): ProductTypeB = {
    withSQL {
      insert.into(ProductTypeB).columns(
        column.productId,
        column.bCount
      ).values(
        productId,
        bCount
      )
    }.update.apply()

    ProductTypeB(
      productId = productId,
      bCount = bCount)
  }

  def save(entity: ProductTypeB)(implicit session: DBSession = autoSession): ProductTypeB = {
    withSQL {
      update(ProductTypeB).set(
        column.productId -> entity.productId,
        column.bCount -> entity.bCount
      ).where.eq(column.productId, entity.productId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: ProductTypeB)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(ProductTypeB).where.eq(column.productId, entity.productId) }.update.apply()
  }
        
}
