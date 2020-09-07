import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import PropTypes from 'prop-types';

import './style.css';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';

class WritingPost extends Component {
  editorRef = React.createRef();

  goBack = () => {
    this.props.history.goBack();
  };

  submitPost = () => {
    console.log('submitPost');
    console.log(this.editorRef.current.getInstance().getHtml());
    this.editorRef.current.getInstance().getHtml();
  };

  render() {
    const name = this.props.name;
    const category = this.props.category;

    console.log('name' + name);
    console.log('category' + category);

    console.log('짜증');

    return (
      <>
        <header className="Ext-header">
          <a className="Header-left" onClick={this.goBack}>
            <img src="/images/close.png" />
          </a>
          <div className="Header-title">꿀팁 공유</div>
          <a className="Header-right" onClick={this.submitPost}>
            완료
          </a>
          <hr className="Header-bottom" />
        </header>
        <form>
          <div className="InputDiv" style={{ top: 138 }}>
            <span className="InputTitle">태그 입력 : </span>
            <input type="text" className="InputText" />
          </div>
          <div className="InputDiv">
            <span className="InputTitle">제목 : </span>
            <input type="text" className="InputText" />
          </div>
          <Editor
            placeholder="당신의 꿀팁을 작성해주세요!"
            previewStyle="vertical"
            height="430px"
            initialEditType="wysiwyg"
            language="ko_KR"
            useCommandShortcut={true}
            ref={this.editorRef}
          />
        </form>
      </>
    );
  }
}

export default withRouter(WritingPost);

// WritingPost.defaultProps = {
//   name: '',
//   category: '',
// };

WritingPost.propTypes = {
  name: PropTypes.string,
  category: PropTypes.string,
};
