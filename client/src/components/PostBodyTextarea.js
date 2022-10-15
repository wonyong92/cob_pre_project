import styled from 'styled-components';
import PropTypes from 'prop-types';
import gfm from 'remark-gfm';

import ReactMarkdown from 'react-markdown';

const QuestionBodyTextarea = styled.textarea`
  background: none;
  border: 1px solid #777;
  border-radius: 3px;
  display: block;
  width: 100%;
  box-sizing: border-box;
  padding-bottom: 20px;
  height: 200px;
  margin-bottom: 20px;
  h3 {
    font-weight: 600;
  }
`;

const PreviewArea = styled.div`
  padding: 20px;
  background-color: #f8f9f8;
  border-radius: 5px;
  margin-bottom: 20px;
`;

const PostBodyTextarea = (props) => {
  return (
    <>
      <QuestionBodyTextarea
        value={props.value}
        onChange={(e) => props.handlePostBodyChange(e.target.value)}
        body={props.value}
      />
      <PreviewArea>
        <ReactMarkdown plugins={[gfm]}>{props.value}</ReactMarkdown>
      </PreviewArea>
    </>
  );
};

PostBodyTextarea.propTypes = {
  value: PropTypes.string.isRequired,
  handlePostBodyChange: PropTypes.any,
};

export default PostBodyTextarea;
