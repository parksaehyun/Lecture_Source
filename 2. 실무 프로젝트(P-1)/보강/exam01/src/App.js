//import {Fragment} from 'react';
import Buttons from "./components/button"; // 컴포넌트 가져오기

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
    <Buttons />
    </>
    //</Fragment>
  )
}

export default App;