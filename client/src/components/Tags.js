import { useRef, useState } from 'react';
import styled from 'styled-components';
/* eslint-disable react/prop-types */

const Tags = ({ tags, setTags, tagList }) => {
  const [inputValue, setInputValue] = useState('');
  const [autoComplete, setAutoComplete] = useState([]);
  const [msg, setMsg] = useState(<p></p>);
  const inputEl = useRef(null);

  // 태그추가
  const inputValueChange = (e) => {
    setInputValue(e.target.value);
  };
  const onAddTag = (e) => {
    const filteredList = tagList.filter((el) =>
      el.name.includes(e.target.value)
    );
    setAutoComplete(filteredList);
    if (!inputValue) {
      setAutoComplete([]);
    }
    if (tags.length >= 5) {
      setMsg(<p>Please enter no more than 5 tags.</p>);
    } else if (tags.length <= 0) {
      setMsg(
        <p>
          Please enter at least one tag; enter any alphabets, see a list of
          tags.
        </p>
      );
    } else {
      setMsg(<p></p>);
    }
    if (e.key === 'Enter') {
      for (let j = 0; j < tags.length; j++) {
        if (tags[j].name === inputValue) {
          setInputValue('');
          setAutoComplete([]);
          return;
        }
      }
      for (let i = 0; i < tagList.length; i++) {
        if (tagList[i].name === inputValue) {
          setTags((prev) => {
            return [...prev, { id: tagList[i].id, name: inputValue }];
          });
          setAutoComplete([]);
          setInputValue('');
        }
      }
    }
  };
  // 태그삭제
  const onDeleteTag = (indexToRemove) => {
    let restTags = [];
    restTags = tags.filter((el, index) => {
      return index !== indexToRemove;
    });
    setTags(restTags);
  };
  const onClickedTag = (e) => {
    setInputValue(e.target.textContent);
    setAutoComplete([]);
    inputEl.current.focus();
  };

  return (
    <Container>
      <div className="tagInputBox">
        <ul className="tagWrapper">
          {tags.map((el, index) => {
            return (
              <li key={index}>
                <button className="tag-title">{el.name}</button>
                <div className="close-icon-wrapper">
                  <button
                    className="tag-close-icon"
                    onClick={() => onDeleteTag(index)}
                  >
                    &times;
                  </button>
                </div>
              </li>
            );
          })}
        </ul>
        <div className="inputWrapper">
          <input
            onKeyUp={(e) => {
              onAddTag(e);
            }}
            onChange={inputValueChange}
            value={inputValue}
            placeholder={tags.length === 0 ? 'e.g. (react)' : null}
            ref={inputEl}
          ></input>
        </div>
      </div>
      {msg}
      <div className="autoCompleteWrapper">
        {autoComplete.map((el, index) => {
          return (
            <button key={index} onClick={onClickedTag}>
              {el.name}
            </button>
          );
        })}
      </div>
    </Container>
  );
};
export default Tags;

const Container = styled.div`
  .tagInputBox {
    display: flex;
    justify-content: flex-start;
    border: 1px solid #777;
    width: 816px;
    border-radius: 3px;
    min-height: 45px;
    flex-wrap: wrap;
    white-space: normal;
  }
  p {
    margin-top: 5px;
    color: #de4f54;
  }
  ul {
    display: flex;
    flex-wrap: wrap;
    li {
      margin: 5px;
      display: flex;
      padding-left: 5px;
      padding-right: 5px;
      border-radius: 3px;
      list-style: none;
      background-color: #e1ecf4;
    }
    .tag-title {
      height: 30px;
      border: none;
      border-radius: 3px;
      background-color: transparent;
      white-space: nowrap;
      color: #39739d;
    }
    .close-icon-wrapper {
      display: flex;
      justify-content: center;
      align-items: center;
      align-self: center;
      height: 16px;
      width: 16px;
      margin-left: 5px;
      .tag-close-icon {
        cursor: pointer;
        font-size: 25px;
        border: none;
        background-color: transparent;
        color: #39739d;
      }
      .tag-close-icon:hover {
        color: #e1ecf4;
      }
    }
    .close-icon-wrapper:hover {
      background-color: #39739d;
      border-radius: 3px;
    }
  }
  .inputWrapper {
    display: flex;
    input {
      display: inline-block;
      min-width: 19px;
      border: none;
      text-indent: 8px;
      outline: none;
      box-shadow: none;
      background-color: transparent;
      min-height: 35px;
      box-sizing: content-box;
    }
  }
  .autoCompleteWrapper {
    width: 500px;
    height: auto;
    background-color: white;
    display: flex;
    flex-wrap: wrap;
    position: absolute;
    top: 678px;
    box-shadow: 0 4px 6px rgb(32 33 36 / 10%);
    button {
      flex-basis: 20px;
      height: 30px;
      border-radius: 5px;
      margin: 3px;
      background-color: #e1ecf4;
      padding: 7px;
      border: none;
      color: #39739d;
      cursor: pointer;
      white-space: nowrap;
    }
  }
`;
