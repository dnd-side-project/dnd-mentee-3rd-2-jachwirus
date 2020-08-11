import React from 'react';
import GoodTip from '../GoodTip';
import RecentChange from '../RecentChange'
import './index.css';
import SearchBar from '../SearchBar';

function HomePage() {
  return (
    <header className="HomePage">
      <div id="search">
        <SearchBar/> 
      </div>
      <div className="Container" id="post">
        <GoodTip/>
        <RecentChange/>
      </div>
    </header>
  );
}

export default HomePage;