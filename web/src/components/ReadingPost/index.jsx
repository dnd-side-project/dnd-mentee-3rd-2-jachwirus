import React, { useState } from 'react';
import { useAsync } from 'react-async';

import PostApi from '../Api/PostApi';
import axios from 'axios';

import './style.css';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import { Viewer } from '@toast-ui/react-editor';

const getPost = async ({ postId }) => {
  const res = await PostApi().getDocumentById(postId);
  return res.data;
};

const ReadingPost = (props) => {
  const category = props.match.params.category;
  const categoryKey = props.match.params.categoryKey;
  const postId = props.match.params.postId;
  const [title, setTitle] = useState('');
  const [createDt, setCreateDt] = useState('');
  const [contents, setContents] = useState('');

  const ViewerComponet = () => {
    const { data, error, isLoading } = useAsync({
      promiseFn: getPost,
      postId: props.match.params.postId,
    });
    if (isLoading) return 'Loading...';
    if (error) return `Something went wrong: ${error.message}`;
    if (data) {
      axios.get(data.last_version.data_url).then((res) => {
        setTitle(data.title);
        const created_at = data.created_at;
        setCreateDt(created_at.substr(0, 10).replaceAll('-', '.'));
        setContents(res.data);
      });
      return <Viewer initialValue={contents} />;
    }
  };

  // close 버튼 클릭 시 선택했던 카테고리로 목록으로 이동
  const goPrevious = () => {
    props.history.push(`/category/${categoryKey}`);
  };

  // 게시물 수정 페이지로 이동
  const goPostEdit = () => {
    props.history.push(`/writing/${category}/${categoryKey}/${postId}`);
  };

  return (
    <>
      <header className="Ext-header">
        <div className="Header-title">{props.match.params.category} 꿀팁</div>
        <a className="Header-left" onClick={goPrevious}>
          <img src="/images/close.png" />
        </a>
        <a className="Header-right" onClick={goPostEdit}>
          수정
        </a>
        <hr className="Header-bottom" />
      </header>
      <div className="Div-Cotents">
        <div className="Post-Info">
          <span className="Viewer-Title">{title}</span>
          <span className="Viewer-Date">{createDt}</span>
        </div>
        <div className="Post-Contents">
          <ViewerComponet />
        </div>
      </div>
    </>
  );
};
export default ReadingPost;
