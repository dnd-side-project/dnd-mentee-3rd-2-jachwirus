import React, { useState } from "react";

import "./style.css";
import PostPreview from "../PostPreview";
import IconWithText from "../IconWithText";
import CreatePostButton from "../CreatePostButton";
import useFetch from "../../hooks/useFetch";
import { readApi } from "../../assets/uri";

const InCategory = ({ match }) => {
  const [name, setName] = useState("다운타운베이비");
  const [data, setData] = useState([]);

  const { key } = match.params;

  const api = `${readApi}/documents`;
  const option = {
    params: {
      category: "laundry",
    },
  };
  const callback = (data) => {
    setData(data.content);
  };
  const loading = useFetch({ api, option, callback });

  console.log("RENDERING!!!", loading);

  const keyData = {
    cleaning: {
      title: "청소",
    },
    bathroom: {
      title: "화장실",
    },
    law: {
      title: "법률",
    },
    separateCollection: {
      title: "분리수거",
    },
    move: {
      title: "이사",
    },
    cooking: {
      title: "요리",
    },
    pet: {
      title: "애완견",
    },
  };

  const getPreviewList = () => {
    if (loading) {
      return "loading....";
    } else if (loading === false) {
      return data.map(({ id, title, thumbnailURL, likes }) => (
        <PostPreview
          key={id}
          title={title}
          thumbnailURL={thumbnailURL}
          likes={likes}
        />
      ));
    } else {
      //undefined
    }
  };

  return (
    <div>
      <h3 className="goodTipTitle">{keyData[key].title} 꿀팁</h3>
      <hr id="line" />
      <CreatePostButton name={name} />
      <hr id="line" />
      <div id="background">
        <div id="buttonContainer">
          <div id="categoryTitle">{keyData[key].title} 카테고리</div>
          <IconWithText
            className="sequence"
            image="good.png"
            text="추천 순"
            width="100"
          />
          <IconWithText
            className="sequence"
            image="new.png"
            text="최신 순"
            width="100"
          />
        </div>
        <div className="gridContainer">{getPreviewList()}</div>
      </div>
    </div>
  );
};

export default InCategory;
