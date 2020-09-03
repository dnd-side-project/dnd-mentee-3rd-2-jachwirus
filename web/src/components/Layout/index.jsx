import React from 'react';

import Header from '../Header';
import BottomMenu from '../BottomMenu';
import './style.css';
import { useLocation } from 'react-router-dom';

const Layout = ({ children }) => {
  const data = {
    mode: 1,
    // header: { title: '자취ers' },
    header: { curPath: useLocation().pathname },
    bottom_menu: [
      {
        id: 1,
        title: 'Home',
        link: '/',
        img: 'house.png',
        txt: '홈',
      },
      { id: 2, title: 'Menu', link: 'menu', img: 'menu.png', txt: '커뮤니티' },
      {
        id: 3,
        title: 'Writing',
        link: 'writing',
        img: 'edit.png',
        txt: '지도',
      },
      {
        id: 4,
        title: 'MyPage',
        link: 'mypage',
        img: 'avatar.png',
        txt: '프로필',
      },
    ],
  };
  return (
    <div className="Layout">
      {/* <Header className="App-header" title={data.header.title}></Header> */}
      <Header className="App-header" curPath={data.header.curPath}></Header>
      <div className="App-page">{children}</div>
      <BottomMenu
        className="App-menu"
        data={data.bottom_menu}
        curPath={data.header.curPath}
      ></BottomMenu>
    </div>
  );
};

export default Layout;
