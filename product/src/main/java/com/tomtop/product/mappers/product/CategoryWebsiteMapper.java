package com.tomtop.product.mappers.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tomtop.product.models.dto.CategoryPlatformDto;
import com.tomtop.product.models.dto.CategoryWebsiteWithNameDto;

public interface CategoryWebsiteMapper {

	/**
	 * 查询类目
	 * 
	 * @author lijun
	 * @param paras
	 * @return
	 */
	List<CategoryWebsiteWithNameDto> getCategories(Map<String, Object> paras);

	@Select("SELECT p.* FROM t_category_website p where p.iid=#{0}")
	CategoryWebsiteWithNameDto getCategoryWebsiteById(int id);

	@Select("SELECT p.*, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE iparentid IS NULL "
			+ "AND n.ilanguageid = #{0} AND p.iwebsiteid = #{1} ORDER BY iposition ")
	List<CategoryWebsiteWithNameDto> getRootCategories(int languageid,
			int websiteid);

	@Select("SELECT p.*, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE iparentid = #{0} " + "AND n.ilanguageid = #{1} "
			+ "AND p.iwebsiteid = #{2} ORDER BY iposition ")
	List<CategoryWebsiteWithNameDto> getChildCategoriesAll(
			int parentCategoryId, int languageid, int websiteid);

	@Select("SELECT p.*, n.cname,m.curl as cbgimglink,m.iid as ibgimageid  FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "LEFT  JOIN t_category_backgroundimages m ON m.icategorynameid = n.iid "
			+ "WHERE iparentid IS NULL and p.ilevel=1 "
			+ "AND n.ilanguageid = #{0} AND p.iwebsiteid = #{1} AND  p.bshow=#{2} ORDER BY iposition ")
	List<CategoryWebsiteWithNameDto> getRootCategoriesByDisplay(int languageid,
			int websiteid, boolean display);

	@Select("SELECT p.*, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE iparentid = #{0} " + "AND n.ilanguageid = #{1} "
			+ "AND p.iwebsiteid = #{2} and p.bshow=true ORDER BY iposition")
	List<CategoryWebsiteWithNameDto> getChildCategories(int parentCategoryId,
			int languageid, int websiteid);

	@Select({
			"<script>",
			"SELECT p.*, n.cname FROM t_category_website p "
					+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
					+ "WHERE iparentid IN "
					+ "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>#{item}</foreach> "
					+ "AND n.ilanguageid = #{1} "
					+ "AND p.iwebsiteid = #{2} ORDER BY iposition", "</script>" })
	List<CategoryWebsiteWithNameDto> getMultiChildCategories(
			@Param("list") List<Integer> parentCategoryIds, int languageid,
			int websiteid);

	@Select({
			"<script>",
			"SELECT p.*, n.cname FROM t_category_website p "
					+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
					+ "WHERE iparentid IN "
					+ "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>#{item}</foreach> "
					+ "AND n.ilanguageid = #{1} "
					+ "AND p.iwebsiteid = #{2} AND p.bshow = #{3} ORDER BY iposition",
			"</script>" })
	List<CategoryWebsiteWithNameDto> getMultiChildCategoriesByDisplay(
			@Param("list") List<Integer> parentCategoryIds, int languageid,
			int websiteid, boolean display);

	@Select("SELECT p.*, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE p.icategoryid = #{0} AND n.ilanguageid = #{1} "
			+ "AND p.iwebsiteid = #{2} and p.bshow=true ORDER BY iposition")
	CategoryWebsiteWithNameDto getCategory(int categoryId, int languageid,
			int websiteid);

	@Select("SELECT p.*, n.cname,n.iid as nameid,n.ccontent FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE iparentid = (select iid from t_category_website where cpath = #{0} and iwebsiteid=#{2}) "
			+ "AND n.ilanguageid = #{1} "
			+ "AND p.iwebsiteid = #{2} and p.bshow=true ORDER BY iposition")
	List<CategoryWebsiteWithNameDto> getChildCategoriesByPath(String cpath,
			int languageid, int websiteid);

	@Select("SELECT * from t_category_website p "
			+ "WHERE p.icategoryid = #{0} "
			+ "AND p.iwebsiteid = #{1} ORDER BY iposition")
	CategoryPlatformDto getPlatformCategories(int categoryId, int websiteid);

	/*@Select("SELECT p.iid, p.iwebsiteid, p.icategoryid, p.iparentid, p.cpath, "
			+ "p.ilevel, p.iposition,p.ichildrencount, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid and n.ilanguageid = #{0} "
			+ "WHERE p.iwebsiteid = #{1} ORDER BY iposition")
	List<CategoryWebsiteWithNameDto> getCategories(int languageid, int websiteid);*/

	List<CategoryWebsiteWithNameDto> getCategoriesByListingids(
			@Param("list") List<String> listingID, int languageid, int websiteid);

	@Select("SELECT p.iid, p.iparentid, n.cname,p.cpath,n.icategoryid FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE n.ilanguageid = #{0} AND p.iwebsiteid = #{1} and p.ilevel = #{2} ORDER BY iposition")
	List<CategoryWebsiteWithNameDto> getCategoriesByLevel(int languageid,
			int websiteid, int level);

	@Select("SELECT p.iparentid FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE  p.iwebsiteid = #{0} and p.icategoryid = #{1} limit 1")
	Integer getCategoryParentIdBycategoryId(int websiteid, int categoryId);

	@Select({
			"<script>",
			"SELECT n.cname,p.cpath from t_category_website p ",
			"INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid ",
			"WHERE n.ilanguageid = #{0} AND p.iwebsiteid = #{1} and n.cname in ",
			"<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>#{item}</foreach> ",
			" ORDER BY iposition", "</script>" })
	List<CategoryWebsiteWithNameDto> getCategoriesByNames(int languageid,
			int websiteid, @Param("list") List<String> names);

	/*
	 * @Select(
	 * "select tw1.icategoryid as iid, tw1.iparentid as iparentid ,tw1.ilevel as ilevel "
	 * + "from t_category_website tw1 " +
	 * "inner join t_category_website tw2 on tw2.iparentid = tw1.icategoryid " +
	 * "and tw1.iwebsiteid = tw2.iwebsiteid where tw2.icategoryid = #{1} and tw2.iwebsiteid = #{0}"
	 * ) CategoryBase getParentCategoryIdBycategoryId(Integer siteId, Integer
	 * categoryId);
	 */

	@Select("select iid from t_category_website where ilevel=1 and iparentid is null and iwebsiteid=#{0}")
	List<Integer> getAllRootCategoryIdBySite(Integer siteId);

	@Select("SELECT p.*, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE iparentid = #{0} " + "AND n.ilanguageid = #{1} "
			+ "AND p.iwebsiteid = #{2} and p.bshow=true ORDER BY iposition")
	List<CategoryWebsiteWithNameDto> getChildCategoriesByBshow(
			int parentCategoryId, int languageid, int websiteid);

	@Select({
			"<script>",
			"select p.*, n.cname from t_category_website p ",
			"inner join t_category_name n on n.icategoryid = p.icategoryid where 1=1 ",
			"<if test=\"list!=null and list.size()>0\">",
			"and p.icategoryid in ",
			"<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>#{item}</foreach> ",
			"</if>", "and n.ilanguageid = #{1} ",
			"and p.iwebsiteid = #{2} and p.bshow=true ORDER BY iposition",
			"</script>" })
	List<CategoryWebsiteWithNameDto> getCategoriesByCategoryIds(
			@Param("list") List<Integer> ids, int languageid, int websiteid);

	@Select("SELECT p.*, n.cname FROM t_category_website p "
			+ "INNER JOIN t_category_name n ON n.icategoryid = p.icategoryid "
			+ "WHERE iparentid = #{0} AND n.icategoryid > #{1} AND n.ilanguageid = #{2} "
			+ "AND p.iwebsiteid = #{3} AND p.bshow=true ORDER BY iposition")
	List<CategoryWebsiteWithNameDto> getNewChildCategory(int categoryId,
			int maxId, int languageid, int websiteid);

}