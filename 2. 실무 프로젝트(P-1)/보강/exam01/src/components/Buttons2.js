// 클래스형 컴포넌트
import React, {Component} from "react"; // {Component} 를 상속받으면 클래스형 컴포넌트
class Buttons2 extends Component { // 파일명과 컴포넌트명을 동일하게 만드는게 관례
    render() {
        const { color, children } = this.props;

    console.log('props', Object.getOwnPropertyDescriptors(this.props));
    console.log('props', Object.isExtensible(this.props));
    console.log('props', Object.isFrozen(this.props));
    const styles = {
      background: color,
    };

    return <button style={styles}>{children}</button>;
    }
}

export default Buttons2;