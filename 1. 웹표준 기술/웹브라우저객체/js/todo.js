const todo = {
  /** 
   * 스케줄 추가 
   * 
   * 
   */
  
   add() {
       const subject = frmRegist.subject.value;

       if (!subject.trim()) {  //trim : 좌우 공백 제거
           alert("할일을 입력하세요.");
           return; // 할일을 입력하세요 팝업 뜨고 나오는 삭제리스트 안나오게 return은 더 이상 함수가 진행되지 않도록 하는 기능?
       }

       const liEl = document.createElement("li");
       liEl.appendChild(document.createTextNode(subject));

       const buttonEl = document.createElement("button");
       buttonEl.appendChild(document.createTextNode("삭제"));
       liEl.appendChild(buttonEl);

       const itemsEl = document.querySelector(".items");
       itemsEl.appendChild(liEl);
       
       //스케줄삭제
       buttonEl.addEventListener("click", function() {
           itemsEl.removeChild(liEl);
       });

       frmRegist.subject.value =""; //입력하고 바로 삭제
       frmRegist.subject.focus(); //커서 깜빡깜빡
       
   },
}; //객체 한개 더 만들기

window.addEventListener("DOMContentLoaded", function() {
   frmRegist.addEventListener("submit", function (e) {
       e.preventDefault(); //기본동작 차단, 양식이 넘어가지 않고 머무르도록
       todo.add(); //스케줄 추가 (바인딩?)
   });
});