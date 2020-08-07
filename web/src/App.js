import React, {Component} from 'react';
//import logo from './logo.svg';
import './App.css';
import Header from './components/Header';
import BottomMenu from './components/BottomMenu';
import HomePage from './components/HomePage';
import MenuPage from './components/MenuPage';
import WritingPage from './components/WritingPage';
import MyPage from './components/MyPage';

class App extends Component {
  constructor(props){ //render보다 먼저 실행됨, 초기화 담당
    super(props);
    this.state = { //내부적으로 사용할 상태 -> state
      mode: 1,
      header:{title:'자취러스', sub:'World Wide Web!'},
      welcome:{title:'Welcome', desc:'Hello, React!!'},
      bottom_menu:[
        {id: 1, title: 'Home', desc: 'HTML is for information', img: 'house.png'},
        {id: 2, title: 'Menu', desc: 'CSS is for design', img:'menu.png'},
        {id: 3, title: 'Writing', desc: 'JavaScript is for interactive', img: 'edit.png'},
        {id: 4, title: 'MyPage', desc: 'JavaScript is for interactive', img: 'avatar.png'}
      ]
    }
  }
  render(){
    var page = <HomePage></HomePage>;
    if(this.state.mode === 1){
      page = <HomePage></HomePage>
    } else if(this.state.mode === 2){
      page = <MenuPage></MenuPage>
    } else if(this.state.mode === 3){
      page = <WritingPage></WritingPage>
    } else if(this.state.mode === 4){
      page = <MyPage></MyPage>
    }

    return (
      <div className="App">
        <Header className="App-header"
            title={this.state.header.title}
            //sub={this.state.subject.sub}
            onChangePage={function(){
              this.setState({mode:1}); //자취러스 헤더를 누르면 홈으로 감
            }.bind(this)}>
          </Header>
          <div className="App-page">{page}</div>
          <BottomMenu className="App-menu"
            onChangePage={function(id){
            this.setState({
              mode:Number(id)
            });
          }.bind(this)} 
          data={this.state.bottom_menu}></BottomMenu>
      </div>
    );
  }
}

export default App;
