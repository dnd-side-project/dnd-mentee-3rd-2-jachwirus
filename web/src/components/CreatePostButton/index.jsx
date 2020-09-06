import React from "react";

import "./style.css";

const CreatePostButton = ({ name }) => {
  return (
    <div>
      <div id="info">
        <img
          className="profileImage"
          src="/images/profileImage.png"
          alt="profileImage"
        />
        <strong>{name}님</strong>, <br />
        당신의 <span id="goodTipText">꿀팁</span>을 공유해주세요!
      </div>
      <button className="btnCreatePost">꿀팁 공유하러가기</button>
    </div>
  );
};

export default CreatePostButton;
