package example.scalikejdbc.models

import scalikejdbc._

case class Product(
  productId: Int, 
  categoryId: Int, 
  productName: String, 
  productType: Int) {

  def save()(implicit session: DBSession = Product.autoSession): Product = Product.save(this)(session)

  def destroy()(implicit session: DBSession = Product.autoSession): Unit = Product.destroy(this)(session)

}
      

object Product extends SQLSyntaxSupport[Product] {

  override val schemaName = Some("orm_test")

  override val tableName = "product"

  override val columns = Seq("product_id", "category_id", "product_name", "product_type")

  def apply(p: SyntaxProvider[Product])(rs: WrappedResultSet): Product = apply(p.resultName)(rs)
  def apply(p: ResultName[Product])(rs: WrappedResultSet): Product = new Product(
    productId = rs.get(p.productId),
    categoryId = rs.get(p.categoryId),
    productName = rs.get(p.productName),
    productType = rs.get(p.productType)
  )
      
  val p = Product.syntax("p")

  override val autoSession = AutoSession

  def find(productId: Int)(implicit session: DBSession = autoSession): Option[Product] = {
    withSQL {
      select.from(Product as p).where.eq(p.productId, productId)
    }.map(Product(p.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[Product] = {
    withSQL(select.from(Product as p)).map(Product(p.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(Product as p)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Product] = {
    withSQL { 
      select.from(Product as p).where.append(sqls"${where}")
    }.map(Product(p.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(Product as p).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    productId: Int,
    categoryId: Int,
    productName: String,
    productType: Int)(implicit session: DBSession = autoSession): Product = {
    withSQL {
      insert.into(Product).columns(
        column.productId,
        column.categoryId,
        column.productName,
        column.productType
      ).values(
        productId,
        categoryId,
        productName,
        productType
      )
    }.update.apply()

    Product(
      productId = productId,
      categoryId = categoryId,
      productName = productName,
      productType = productType)
  }

  def save(entity: Product)(implicit session: DBSession = autoSession): Product = {
    withSQL {
      update(Product).set(
        column.productId -> entity.productId,
        column.categoryId -> entity.categoryId,
        column.productName -> entity.productName,
        column.productType -> entity.productType
      ).where.eq(column.productId, entity.productId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: Product)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(Product).where.eq(column.productId, entity.productId) }.update.apply()
  }
        
}
