import React from "react";

import "./style.css";

const PostPreview = ({ title, thumbnailURL, likes }) => {
  return (
    <div>
      <button className="postPreview">
        <div className="profile">
          <img
            className="profileImageInPost"
            src={thumbnailURL}
            alt="profileImage"
          />
        </div>
        <div id="postTitle">{title}</div>
        {/*{description}*/}
      </button>
    </div>
  );
};

export default PostPreview;
