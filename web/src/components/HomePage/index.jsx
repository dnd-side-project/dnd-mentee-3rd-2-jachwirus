import React from "react";

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
          title: "이러쿵 저러쿵한데 저러쿵한 사람",
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
      <img className="homeImage" src="/images/house.png" alt="homeImage" />
      <SearchBar />
      <CategoryCollection />
      <hr className="Line" />
      <GoodTip title={data.hotPost.name} posts={data.hotPost.posts}></GoodTip>
      <hr className="Line" />
      <GoodTip
        title={data.recentPost.name}
        posts={data.recentPost.posts}
      ></GoodTip>
    </header>
  );
}

export default HomePage;
