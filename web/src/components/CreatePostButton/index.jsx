import React from 'react';
import { Link } from 'react-router-dom';

import './style.css';

const CreatePostButton = ({ name, category }) => {
  return (
    <div>
      <div id="info">
        <img className="profileImage" src="/images/profileImage.png" alt="profileImage" />
        <strong>{name}님</strong>, <br />
        당신의 <span id="goodTipText">꿀팁</span>을 공유해주세요!
      </div>
      <Link to="/writing" name={name} category={category}>
        <button className="btnCreatePost">꿀팁 공유하러가기</button>
      </Link>
    </div>
  );
};

export default CreatePostButton;
