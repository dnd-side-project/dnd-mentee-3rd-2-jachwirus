import React from "react";

import GoodTip from "../GoodTip";
import RecentChange from "../RecentChange";
import SearchBar from "../SearchBar";
import "./style.css";

function HomePage() {
  return (
    <header className="HomePage">
      <SearchBar />
      <GoodTip />
      <RecentChange />
    </header>
  );
}

export default HomePage;
