import React from 'react';
import GoodTip from '../GoodTip';
import RecentChange from '../RecentChange'
import './index.css';
import findImage from '../../images/magnifying-glass.png';

function HomePage() {
  return (
    <header className="HomePage">
      <form action="/find_process" method="post"
        onSubmit={function(e){
            e.preventDefault();
            //debugger;
            // this.props.onSubmit(
            //     e.target.find.value,
            //     e.target.desc.value
            // );
        }.bind(this)}
        className="Container"
        id="find"
      >
        <input className="Find" type="text" name="find" placeholder="해시태그 검색"></input>
        <input className="FindButton" type="image" src={findImage} alt="submit"></input>
      </form>
      <p className="Container" id="post">
        <GoodTip/>
        <RecentChange/>
      </p>
    </header>
  );
}

export default HomePage;