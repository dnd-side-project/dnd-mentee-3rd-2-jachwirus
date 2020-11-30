import React, { useRef, useState } from 'react';
import { withRouter } from 'react-router-dom';
import { useAsync } from 'react-async';
import PropTypes from 'prop-types';
import PostApi from '../Api/PostApi';
import axios from 'axios';

import './style.css';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';

const WritingPost = (props) => {
  // tuiEditor ref
  const editorRef = useRef();
  // 게시물id (신규:'new', 수정:id(number))
  const postId = props.match.params.postId;

  const [title, setTitle] = useState('');
  const [hashTag, setHashTag] = useState('');
  const [tagSpan, setTagSpan] = useState([{ tagId: 0, tag: props.match.params.category }]);
  const [nextId, setNextId] = useState(1);
  const [contents, setContents] = useState('');

  // 게시물 읽어오기
  // const getPostApi = async ({ postId }) => {
  //   const res = await PostApi().getDocumentById(postId);
  //   return res.data;
  // };

  // const getPost = () => {

  // };

  // 신규 작성 여부에 따른 화면 구성
  const EditForm = () => {
    if (postId !== 'new') {
      PostApi()
        .getDocumentById(postId)
        .then((res) => {
          axios.get(res.data.last_version.data_url).then((doc) => {
            setTitle(res.data.title);
            setContents(doc.data);
            return (
              <>
                <FormTitle />
                <FormEditor />
              </>
            );
          });
        });
    } else {
      return (
        <>
          <FormHashTag />
          <FormTitle />
          <FormEditor />
        </>
      );
    }
  };
  // const EditForm = () => {
  //   if (postId !== 'new') {
  //     const { data, error, isLoading } = useAsync({
  //       promiseFn: getPostApi,
  //       postId: postId,
  //     });
  //     if (isLoading) return 'Loading...';
  //     if (error) return `Something went wrong: ${error.message}`;
  //     if (data) {
  //       axios.get(data.last_version.data_url).then((res) => {
  //         setTitle(data.title);
  //         setContents(res.data);
  //         return (
  //           <>
  //             <FormTitle />
  //             <FormEditor />
  //           </>
  //         );
  //       });
  //     }
  //   } else {
  //     return (
  //       <>
  //         <FormHashTag />
  //         <FormTitle />
  //         <FormEditor />
  //       </>
  //     );
  //   }
  // };

  const FormHashTag = () => {
    return (
      <>
        <div className="InputDiv" style={{ top: 138 }}>
          <span className="InputTitle">태그 입력 : </span>
          <input
            type="text"
            className="InputText"
            placeholder="태그 작성 후 엔터"
            onKeyDown={pushHashTag}
            onChange={onTagChage}
            value={hashTag}
          />
        </div>
        <div className="DivTag">{spanTagList}</div>
      </>
    );
  };

  const FormTitle = () => {
    return (
      <div className="InputDiv">
        <span className="InputTitle">제목 : </span>
        <input type="text" className="InputText" onChange={onTitleChage} value={title} />
      </div>
    );
  };

  const FormEditor = () => {
    return (
      <Editor
        placeholder="당신의 꿀팁을 작성해주세요!"
        previewStyle="vertical"
        height="430px"
        initialEditType="wysiwyg"
        language="ko-KR"
        useCommandShortcut={true}
        initialValue={contents}
        ref={editorRef}
      />
    );
  };

  // 이전페이지로 이동
  const goBack = () => {
    props.history.goBack();
  };

  // 글 작성 완료(제출)
  const submitPost = () => {
    const tagList = tagSpan.map((tags) => tags.tag);

    const request = {
      document: {
        category: props.match.params.category,
        title: title,
      },
      content: editorRef.current.getInstance().getHtml(),
      hashtags: tagList,
      user_id: '19',
      //user_id: props.match.params.name,
    };

    PostApi()
      .createDocument(request)
      .then((res) => {
        console.log('==response.data================');
        console.log(res.data);
        alert('작성완료하였습니다.');
        props.history.push(
          `/reading/${props.match.params.category}/${props.match.params.categoryKey}/${res.data.id}`,
        );
      })
      .catch((err) => {
        console.log('==err==========================');
        console.log(err);
      })
      .finally(() => {});
  };

  const onTagChage = (e) => {
    setHashTag(e.target.value);
  };

  // 태그 추가
  const pushHashTag = (e) => {
    if (e.keyCode === 13) {
      // 중복 태그 인덱스
      const overlapIdx = tagSpan.findIndex((tags) => tags.tag === hashTag);
      // 중복 태그는 추가 불가
      if (overlapIdx === -1) {
        const nextTags = tagSpan.concat({ tagId: nextId, tag: hashTag });
        setNextId(nextId + 1);
        setTagSpan(nextTags);
      }
      setHashTag('');
    }
  };

  // 태그 삭제
  const onDeleteTag = (tagId) => {
    if (tagId === 0) {
      alert('카테고리 태그는 삭제할 수 없습니다.');
      return false;
    }
    const nextTags = tagSpan.filter((tags) => tags.tagId !== tagId);
    setTagSpan(nextTags);
  };

  const spanTagList = tagSpan.map((tags) => (
    <span className="ChipsTag" key={tags.tagId}>
      #{tags.tag}
      <a
        className="BtnDelete"
        onClick={() => {
          onDeleteTag(tags.tagId);
        }}
      >
        &nbsp;&nbsp;
        <img className="IconDelete" src="/images/delete.png" />
      </a>
    </span>
  ));

  const onTitleChage = (e) => {
    setTitle(e.target.value);
  };

  return (
    <>
      <header className="Ext-header">
        <div className="Header-title">꿀팁 공유</div>
        <a className="Header-left" onClick={goBack}>
          <img src="/images/close.png" />
        </a>
        <a className="Header-right" onClick={submitPost}>
          완료
        </a>
        <hr className="Header-bottom" />
      </header>
      <form>
        <EditForm />
      </form>
    </>
  );
};

export default withRouter(WritingPost);

// WritingPost.defaultProps = {
//   name: '',
//   category: '',
// };

WritingPost.propTypes = {
  name: PropTypes.string,
  category: PropTypes.string,
};
