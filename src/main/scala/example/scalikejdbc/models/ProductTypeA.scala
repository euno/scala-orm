package example.scalikejdbc.models

import scalikejdbc._

case class ProductTypeA(
  productId: Int, 
  aName: String) {

  def save()(implicit session: DBSession = ProductTypeA.autoSession): ProductTypeA = ProductTypeA.save(this)(session)

  def destroy()(implicit session: DBSession = ProductTypeA.autoSession): Unit = ProductTypeA.destroy(this)(session)

}
      

object ProductTypeA extends SQLSyntaxSupport[ProductTypeA] {

  override val schemaName = Some("orm_test")

  override val tableName = "product_type_a"

  override val columns = Seq("product_id", "a_name")

  def apply(pta: SyntaxProvider[ProductTypeA])(rs: WrappedResultSet): ProductTypeA = apply(pta.resultName)(rs)
  def apply(pta: ResultName[ProductTypeA])(rs: WrappedResultSet): ProductTypeA = new ProductTypeA(
    productId = rs.get(pta.productId),
    aName = rs.get(pta.aName)
  )
      
  val pta = ProductTypeA.syntax("pta")

  override val autoSession = AutoSession

  def find(productId: Int)(implicit session: DBSession = autoSession): Option[ProductTypeA] = {
    withSQL {
      select.from(ProductTypeA as pta).where.eq(pta.productId, productId)
    }.map(ProductTypeA(pta.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[ProductTypeA] = {
    withSQL(select.from(ProductTypeA as pta)).map(ProductTypeA(pta.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(ProductTypeA as pta)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[ProductTypeA] = {
    withSQL { 
      select.from(ProductTypeA as pta).where.append(sqls"${where}")
    }.map(ProductTypeA(pta.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(ProductTypeA as pta).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productId: Int,
    aName: String)(implicit session: DBSession = autoSession): ProductTypeA = {
    withSQL {
      insert.into(ProductTypeA).columns(
        column.productId,
        column.aName
      ).values(
        productId,
        aName
      )
    }.update.apply()

    ProductTypeA(
      productId = productId,
      aName = aName)
  }

  def save(entity: ProductTypeA)(implicit session: DBSession = autoSession): ProductTypeA = {
    withSQL {
      update(ProductTypeA).set(
        column.productId -> entity.productId,
        column.aName -> entity.aName
      ).where.eq(column.productId, entity.productId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: ProductTypeA)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(ProductTypeA).where.eq(column.productId, entity.productId) }.update.apply()
  }
        
}
