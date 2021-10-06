package com.project.med.spark.storage.hdfs
import org.junit.runner.RunWith
import com.project.med.storage.hdfs.Metadata._
import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import java.text.SimpleDateFormat

@RunWith(classOf[JUnitRunner])
class csvToParquetTest extends FunSuite {
  val metadata = Seq(
    StringFieldType("A"),
    IntegerFieldType("B"),
    LongFieldType("C"),
    DateFieldType("D", Some(new SimpleDateFormat("yyyy-MM-dd"))),
    DoubleFieldType("E"),
    LongFieldType("I_UNIQ_KPI")
  )
  test("parse metadata") {
    val metadataStr = "#comment\nA|String\nB|Integer\nC|Long\nD|DATE|YYYY-MM-DD\nE|Double\nI_UNIQ_KPI|String\n#comment"
    val lineSep = "\n"
    val colSep = """\|"""
    val actual = parseMetadata(metadataStr, lineSep, colSep)
    val expected = metadata
    assert(expected == actual)

  }
}