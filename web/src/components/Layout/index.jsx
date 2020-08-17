import React from "react";

import Header from "../Header";
import BottomMenu from "../BottomMenu";
import "./style.css";

const Layout = ({ children }) => {
  const data = {
    mode: 1,
    header: { title: "자취ers" },
    bottom_menu: [
      {
        id: 1,
        title: "Home",
        link: "/",
        img: "house.png",
      },
      { id: 2, title: "Menu", link: "menu", img: "menu.png" },
      {
        id: 3,
        title: "Writing",
        link: "/writing",
        img: "edit.png",
      },
      {
        id: 4,
        title: "MyPage",
        link: "mypage",
        img: "avatar.png",
      },
    ],
  };
  return (
    <div className="Layout">
      <Header className="App-header" title={data.header.title}></Header>
      <div className="App-page">{children}</div>
      <BottomMenu className="App-menu" data={data.bottom_menu}></BottomMenu>
    </div>
  );
};

export default Layout;
