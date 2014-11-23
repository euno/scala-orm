package example.scalikejdbc.models

import scalikejdbc._

case class ProductTag(
  productTagId: Int, 
  productTagName: String) {

  def save()(implicit session: DBSession = ProductTag.autoSession): ProductTag = ProductTag.save(this)(session)

  def destroy()(implicit session: DBSession = ProductTag.autoSession): Unit = ProductTag.destroy(this)(session)

}
      

object ProductTag extends SQLSyntaxSupport[ProductTag] {

  override val schemaName = Some("orm_test")

  override val tableName = "product_tag"

  override val columns = Seq("product_tag_id", "product_tag_name")

  def apply(pt: SyntaxProvider[ProductTag])(rs: WrappedResultSet): ProductTag = apply(pt.resultName)(rs)
  def apply(pt: ResultName[ProductTag])(rs: WrappedResultSet): ProductTag = new ProductTag(
    productTagId = rs.get(pt.productTagId),
    productTagName = rs.get(pt.productTagName)
  )
      
  val pt = ProductTag.syntax("pt")

  override val autoSession = AutoSession

  def find(productTagId: Int)(implicit session: DBSession = autoSession): Option[ProductTag] = {
    withSQL {
      select.from(ProductTag as pt).where.eq(pt.productTagId, productTagId)
    }.map(ProductTag(pt.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[ProductTag] = {
    withSQL(select.from(ProductTag as pt)).map(ProductTag(pt.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(ProductTag as pt)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[ProductTag] = {
    withSQL { 
      select.from(ProductTag as pt).where.append(sqls"${where}")
    }.map(ProductTag(pt.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(ProductTag as pt).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productTagId: Int,
    productTagName: String)(implicit session: DBSession = autoSession): ProductTag = {
    withSQL {
      insert.into(ProductTag).columns(
        column.productTagId,
        column.productTagName
      ).values(
        productTagId,
        productTagName
      )
    }.update.apply()

    ProductTag(
      productTagId = productTagId,
      productTagName = productTagName)
  }

  def save(entity: ProductTag)(implicit session: DBSession = autoSession): ProductTag = {
    withSQL {
      update(ProductTag).set(
        column.productTagId -> entity.productTagId,
        column.productTagName -> entity.productTagName
      ).where.eq(column.productTagId, entity.productTagId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: ProductTag)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(ProductTag).where.eq(column.productTagId, entity.productTagId) }.update.apply()
  }
        
}
