import React from "react";

import "./style.css";

const PostPreview = ({ title /*, description*/ }) => {
  const userName = "밀면애호가";
  const userInfo = "자취마스터";
  return (
    <div>
      <button className="postPreview">
        <div className="profile">
          <img
            className="profileImageInPost"
            src="/images/profileImage.png"
            alt="profileImage"
          />
          <div id="userName">{userName}</div>
          <div id="userInfo">{userInfo}</div>
        </div>
        <div id="postTitle">{title}</div>
        {/*{description}*/}
      </button>
    </div>
  );
};

export default PostPreview;
