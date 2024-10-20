import http from "../http-common";

class ItemCategoryService {
  getAllItemCategorys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/itemCategory/itemCategorys`, searchDTO);
  }

  get(itemCategoryId) {
    return this.getRequest(`/itemCategory/${itemCategoryId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/itemCategory?field=${matchData}`, null);
  }

  addItemCategory(data) {
    return http.post("/itemCategory/addItemCategory", data);
  }

  update(data) {
  	return http.post("/itemCategory/updateItemCategory", data);
  }
  
  uploadImage(data,itemCategoryId) {
  	return http.postForm("/itemCategory/uploadImage/"+itemCategoryId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ItemCategoryService();
