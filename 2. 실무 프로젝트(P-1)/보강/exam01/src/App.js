//import {Fragment} from 'react';
import Buttons from "./components/Buttons2"; // 모듈 가져오기 // 컴포넌트 가져오기
import func, {objA as oA, objB} from "./libs/common";

console.log(oA, objB);

function App() {
  const name = "김이름";
  // {name} : 식임, 변수연산도 가능

  // jsx문법
  return ( 
    // 출력할 내용
    //<Fragment>
    <>
    {name && <h1>안녕하세요! {name}</h1>}
    <h2>반갑습니다.</h2>
    {/* <Buttons color="blue" title="확인" />  */}{/* color="blue" title="확인" : 속성*/}
    <Buttons color="orange">확인</Buttons>
    <Buttons />
    </>
    //</Fragment>
  );
}

export default App; // 모듈 내보내기