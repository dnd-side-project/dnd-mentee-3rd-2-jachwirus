import React from "react";

import PostPreview from "../PostPreview";
import "./style.css";
import IconWithText from "../IconWithText";
import CreatePostButton from "../CreatePostButton";

const InCategory = ({ match }) => {
  const name = "다운타운베이비";
  const keyData = {
    cleaning: {
      title: "청소",
      description: "",
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
    question: {
      title: "???",
    },
  };

  const { key } = match.params;

  const postData = [
    {
      id: 1,
      title: "청소랑 나랑 거리멀다 싶은 사람 다 모여라..",
    },
    { id: 2, title: "집에서 양말 신기만 하면 양말 색변하는 사람" },
    {
      id: 3,
      title: "손 안닫는 곳까지 깨끗하게 청소하는 법",
    },
    { id: 4, title: "청소 한 번으로 데이트 성공하기" },
    { id: 5, title: "부모님 자취 방 1시간 전 티나게 청소하자" },
    { id: 6, title: "화상 회의 때 집 깨끗하게 보이는 방법 공유함" },
  ];

  const postPreviewList = postData.map((elem) => {
    const { id, title, description } = elem;
    return <PostPreview key={id} title={title} description={description} />;
  });

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
        <div className="gridContainer">{postPreviewList}</div>
      </div>
    </div>
  );
};

export default InCategory;
