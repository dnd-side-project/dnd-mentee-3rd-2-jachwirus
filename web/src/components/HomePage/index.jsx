import React from "react";

import Header from "../Header";
import GoodTip from "../GoodTip";
import SearchBar from "../SearchBar";
import "./style.css";
import CategoryCollection from "../CategoryCollection";

function HomePage() {
  const data = {
    hotPost: {
      name: "실시간 인기 꿀팁",
      posts: [
        {
          id: 1,
          title: "이러쿵 저러쿵한데 저러쿵한 사람 다모여봐봐",
          tag: "청소",
        },
        {
          id: 2,
          title: "이러 이러해서 이러저러한 사람 구제해드립니다.",
          tag: "분리수거",
        },
        {
          id: 3,
          title: "화장실 얄궂은 냄새나는 사람들 많죠?",
          tag: "화장실",
        },
      ],
    },
    recentPost: {
      name: "실시간 등록 꿀팁",
      posts: [
        {
          id: 1,
          title: "이러쿵 저러쿵",
          tag: "청소",
        },
        {
          id: 2,
          title: "이러 이러해서",
          tag: "분리수거",
        },
        {
          id: 3,
          title: "화장실 얄궂은",
          tag: "화장실",
        },
      ],
    },
  };
  return (
    <header className="HomePage">
      <Header className="App-header" />
      <img
        className="homeImage"
        src="/images/illustration.png"
        alt="homeImage"
      />

      <div id="mainHome">
        <SearchBar />
        <CategoryCollection />
        <hr className="Line" />
        <GoodTip title={data.hotPost.name} posts={data.hotPost.posts} />
        <hr className="Line" />
        <GoodTip title={data.recentPost.name} posts={data.recentPost.posts} />
      </div>
    </header>
  );
}

export default HomePage;
