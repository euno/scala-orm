package example.scalikejdbc.models

import scalikejdbc._

case class ProductCategory(
  categoryId: Int, 
  productCategoryName: String) {

  def save()(implicit session: DBSession = ProductCategory.autoSession): ProductCategory = ProductCategory.save(this)(session)

  def destroy()(implicit session: DBSession = ProductCategory.autoSession): Unit = ProductCategory.destroy(this)(session)

}
      

object ProductCategory extends SQLSyntaxSupport[ProductCategory] {

  override val schemaName = Some("orm_test")

  override val tableName = "product_category"

  override val columns = Seq("category_id", "product_category_name")

  def apply(pc: SyntaxProvider[ProductCategory])(rs: WrappedResultSet): ProductCategory = apply(pc.resultName)(rs)
  def apply(pc: ResultName[ProductCategory])(rs: WrappedResultSet): ProductCategory = new ProductCategory(
    categoryId = rs.get(pc.categoryId),
    productCategoryName = rs.get(pc.productCategoryName)
  )
      
  val pc = ProductCategory.syntax("pc")

  override val autoSession = AutoSession

  def find(categoryId: Int)(implicit session: DBSession = autoSession): Option[ProductCategory] = {
    withSQL {
      select.from(ProductCategory as pc).where.eq(pc.categoryId, categoryId)
    }.map(ProductCategory(pc.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[ProductCategory] = {
    withSQL(select.from(ProductCategory as pc)).map(ProductCategory(pc.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(ProductCategory as pc)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[ProductCategory] = {
    withSQL { 
      select.from(ProductCategory as pc).where.append(sqls"${where}")
    }.map(ProductCategory(pc.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(ProductCategory as pc).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    categoryId: Int,
    productCategoryName: String)(implicit session: DBSession = autoSession): ProductCategory = {
    withSQL {
      insert.into(ProductCategory).columns(
        column.categoryId,
        column.productCategoryName
      ).values(
        categoryId,
        productCategoryName
      )
    }.update.apply()

    ProductCategory(
      categoryId = categoryId,
      productCategoryName = productCategoryName)
  }

  def save(entity: ProductCategory)(implicit session: DBSession = autoSession): ProductCategory = {
    withSQL {
      update(ProductCategory).set(
        column.categoryId -> entity.categoryId,
        column.productCategoryName -> entity.productCategoryName
      ).where.eq(column.categoryId, entity.categoryId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: ProductCategory)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(ProductCategory).where.eq(column.categoryId, entity.categoryId) }.update.apply()
  }
        
}
