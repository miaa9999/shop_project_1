 // 메인 카테고리 변경 시 실행되는 함수
   function updateSubCategories() {
       var selectedMainCategory = document.getElementById('mainCategory').value;
       var subCategoryDropdown = document.getElementById('subCategory');

       // 서브 카테고리 옵션 초기화
       subCategoryDropdown.innerHTML = '';

       // 선택한 메인 카테고리에 따라 적절한 서브 카테고리 옵션 추가
       if (selectedMainCategory === '인형') {
           subCategoryDropdown.innerHTML += '<option value="사람인형">사람인형</option>';
           subCategoryDropdown.innerHTML += '<option value="동물인형">동물인형</option>';
           subCategoryDropdown.innerHTML += '<option value="캐릭터인형">캐릭터인형</option>';
       } else if (selectedMainCategory === '완구') {
           subCategoryDropdown.innerHTML += '<option value="레고">레고</option>';
           subCategoryDropdown.innerHTML += '<option value="촉각놀이">촉각놀이</option>';
           subCategoryDropdown.innerHTML += '<option value="실외장난감">실외장난감</option>';
       } else if (selectedMainCategory === '도서') {
           subCategoryDropdown.innerHTML += '<option value="동화">동화</option>';
           subCategoryDropdown.innerHTML += '<option value="위인전">위인전</option>';
           subCategoryDropdown.innerHTML += '<option value="영어도서">영어도서</option>';
       }
   }


// 상품 수량 확인 함수
    function checkProductStock(event) {
        var productStockInput = document.getElementById('productStock');
        var errorDiv = document.getElementById('errorDiv');

        // 입력값이 0 또는 음수인지 확인
        if (productStockInput.value <= 0) {
            // 에러 메시지 표시
            errorDiv.innerText = '상품 수량은 0보다 큰 정수값으로 입력하세요.';
        } else {
            // 에러 메시지 숨기기
            errorDiv.innerText = '';
        }
    }

    // 상품 가격 확인 함수
    function checkProductPrice(event) {
        var productPriceInput = document.getElementById('productPrice');
        var errorDiv2 = document.getElementById('errorDiv2');

        // 입력값이 0 또는 음수인지 확인
        if (productPriceInput.value <= 0) {
            // 에러 메시지 표시
            errorDiv2.innerText = '상품 가격은 0보다 큰 정수값으로 입력하세요.';
        } else {
            // 에러 메시지 숨기기
            errorDiv2.innerText = '';
        }
    }

    // 폼 제출 시 실행되는 함수
    function checkFormSubmit(event) {
        var errorDiv = document.getElementById('errorDiv');
        var errorDiv2 = document.getElementById('errorDiv2');
        var productStockInput = document.getElementById('productStock');
        var productPriceInput = document.getElementById('productPrice');

        // 에러 메시지가 있고 상품 수량과 상품 가격이 0 또는 음수일 경우에만 폼 제출 막기
        if (errorDiv.innerText !== '' || productStockInput.value <= 0 || errorDiv2.innerText !== '' || productPriceInput.value <= 0) {
            event.preventDefault();
        }
    }