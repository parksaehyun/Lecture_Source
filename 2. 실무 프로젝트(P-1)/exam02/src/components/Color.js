import { Component } from 'react';

class Color extends Component {
  state = {
    color: 'blue',
    subColor: 'black',
  };

  render() {
    //this -> Color로 생성된 객체의 주소
    const changeColor = (color) => this.setState({color}); // Color
    const changesubColor = (color) => 
    this.setState((prevState) => ({...prevState, subColor: color})); //기존컬러는 유지하고 sub컬러만 바귀게끔

    const { color, subColor } = this.state;
    return (
      <>
        <div
          style={{ background: color, width: '100px', height: '100px' }}
        ></div>
        <button 
        type="button" 
        onClick={() => changeColor('red')} //소괄호만하면 즉, 호출하면 무한루프 돔, 함수값만 대입해야 대기 했다가 클릭했을 때만 동작함, 리액트는 굳이 선택 안하고 값만 넣어도 됨 돔을 선택 할 필요x
        onContextMenu={() => changesubColor('red')}
        >
        RED
        </button>
        <button 
        type="button" 
        onClick={() => changeColor('orange')}
        onContextMenu={ () => changesubColor('orange')}
        >
        ORANGE
        </button>
        <button 
        type="button" 
        onClick={() => changeColor('green')}
        onContextMenu={ () => changesubColor('green')}
        >
        GREEN
        </button>
        <div style={{ background: subColor, width: '50px', height: '50px' }}></div>
      </>
    );
  }
}

export default Color;
