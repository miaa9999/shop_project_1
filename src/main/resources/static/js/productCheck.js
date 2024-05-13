 // 메인 카테고리 변경 시 실행되는 함수
   function updateSubCategories() {
       var selectedMainCategory = document.getElementById('mainCategory').value;
       var subCategoryDropdown = document.getElementById('subCategory');

       // 서브 카테고리 옵션 초기화
       subCategoryDropdown.innerHTML = '';

       // 선택한 메인 카테고리에 따라 적절한 서브 카테고리 옵션 추가
       if (selectedMainCategory === '인형') {
           subCategoryDropdown.innerHTML += '<option value="">서브 카테고리를 선택해주세요</option>';
           subCategoryDropdown.innerHTML += '<option value="사람인형">사람인형</option>';
           subCategoryDropdown.innerHTML += '<option value="동물인형">동물인형</option>';
           subCategoryDropdown.innerHTML += '<option value="캐릭터인형">캐릭터인형</option>';
       } else if (selectedMainCategory === '완구') {
            subCategoryDropdown.innerHTML += '<option value="">서브 카테고리를 선택해주세요</option>';
           subCategoryDropdown.innerHTML += '<option value="레고">레고</option>';
           subCategoryDropdown.innerHTML += '<option value="촉각놀이">촉각놀이</option>';
           subCategoryDropdown.innerHTML += '<option value="실외장난감">실외장난감</option>';
       } else if (selectedMainCategory === '도서') {
            subCategoryDropdown.innerHTML += '<option value="">서브 카테고리를 선택해주세요</option>';
           subCategoryDropdown.innerHTML += '<option value="동화">동화</option>';
           subCategoryDropdown.innerHTML += '<option value="위인전">위인전</option>';
           subCategoryDropdown.innerHTML += '<option value="영어도서">영어도서</option>';
       }
   }


// 상품 수량 확인 함수
function checkProductStock() {
    var productStockInput = document.getElementById('productStock');
    var errorDivStock = document.getElementById('errorDivStock');

    // 입력값이 0 또는 음수인지 확인
    if (productStockInput.value <= 0) {
        // 에러 메시지 표시
        errorDivStock.innerText = '상품 수량은 0보다 큰 정수값으로 입력하세요.';
    } else {
        // 에러 메시지 숨기기
        errorDivStock.innerText = '';
    }
}

// 상품 가격 확인 함수
function checkProductPrice() {
    var productPriceInput = document.getElementById('productPrice');
    var errorDivPrice = document.getElementById('errorDivPrice');

    // 입력값이 0 또는 음수인지 확인
    if (productPriceInput.value <= 0) {
        // 에러 메시지 표시
        errorDivPrice.innerText = '상품 가격은 0보다 큰 정수값으로 입력하세요.';
    } else {
        // 에러 메시지 숨기기
        errorDivPrice.innerText = '';
    }
}

// 상품 이름 및 설명 확인 함수
function checkProductNameAndDescription() {
    var productNameInput = document.getElementById('productName');
    var contentInput = document.getElementById('content');
    var errorDivName = document.getElementById('errorDivName');
    var errorDivDescription = document.getElementById('errorDivDescription');

    // 이름이 비어 있는지 확인
    if (productNameInput.value.trim() === '') {
        // 에러 메시지 표시
        errorDivName.innerText = '상품 이름을 입력하세요.';
    } else {
        // 에러 메시지 숨기기
        errorDivName.innerText = '';
    }

    // 설명이 비어 있는지 확인
    if (contentInput.value.trim() === '') {
        // 에러 메시지 표시
        errorDivDescription.innerText = '상품 설명을 입력하세요.';
    } else {
        // 에러 메시지 숨기기
        errorDivDescription.innerText = '';
    }
}
// 메인 카테고리 및 서브 카테고리 확인 함수
function checkCategory() {
    var mainCategoryInput = document.getElementById('mainCategory');
    var subCategoryInput = document.getElementById('subCategory');
    var errorDivMainCategory = document.getElementById('errorDivMainCategory');
    var errorDivSubCategory = document.getElementById('errorDivSubCategory');

    // 메인 카테고리 선택 여부 확인
    if (mainCategoryInput.value.trim() === '') {
        errorDivMainCategory.innerText = '메인 카테고리를 선택하세요.';
    } else {
        errorDivMainCategory.innerText = '';
    }

    // 서브 카테고리 선택 여부 확인
    if (subCategoryInput.value.trim() === '') {
        errorDivSubCategory.innerText = '서브 카테고리를 선택하세요.';
    } else {
        errorDivSubCategory.innerText = '';
    }
}

// 폼 제출 시 실행되는 함수
function checkFormSubmit(event) {
    // 상품 이름 및 설명 확인
        checkProductNameAndDescription();

        var productStockInput = document.getElementById('productStock');
        var productPriceInput = document.getElementById('productPrice');
        var errorDivStock = document.getElementById('errorDivStock');
        var errorDivPrice = document.getElementById('errorDivPrice');
        var errorDivMainCategory = document.getElementById('errorDivMainCategory');
        var errorDivSubCategory = document.getElementById('errorDivSubCategory');

        // 상품 수량 확인
        checkProductStock();

        // 상품 가격 확인
        checkProductPrice();

        // 메인 카테고리 및 서브 카테고리 확인
        checkCategory();

        // 에러 메시지가 있고 상품 수량과 상품 가격이 0 또는 음수이거나
        // 메인 카테고리 또는 서브 카테고리가 선택되지 않았을 경우에만 폼 제출 막기
        if (errorDivName.innerText !== '' || errorDivDescription.innerText !== '' ||
            productStockInput.value <= 0 || productPriceInput.value <= 0 ||
            errorDivStock.innerText !== '' || errorDivPrice.innerText !== '' ||
            errorDivMainCategory.innerText !== '' || errorDivSubCategory.innerText !== '') {
            event.preventDefault();
        }
}