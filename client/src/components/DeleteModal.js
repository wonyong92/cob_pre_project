import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { getLoginCookie } from '../lib/cookie';
/* eslint-disable react/prop-types */

const ModalBackdrop = styled.div`
  position: fixed;
  z-index: 999;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.4);
  display: grid;
  place-items: center;
`;
const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 360px;
  height: 200px;
  background-color: white;
  box-shadow: 2px 2px 4px gray;
`;

const Top = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 40px;
  margin-left: 40px;
  .title {
    font-size: 25px;
    font-weight: bold;
    margin-bottom: 20px;
  }
  .comment {
    font-size: 18px;
  }
`;
const Bottom = styled.div`
  display: flex;
  justify-content: space-evenly;
  margin-top: 30px;
  .cancel {
    background-color: white;
    border: 1px solid #999999;
    border-radius: 0.5rem;
    width: 80px;
    height: 35px;
    font-weight: bold;
    cursor: pointer;
    &:hover {
      transform: scale(0.95);
    }
  }
  .confirm {
    width: 80px;
    height: 35px;
    background-color: black;
    color: white;
    font-weight: bold;
    border-radius: 0.5rem;
    border: 1px solid black;
    cursor: pointer;
    &:hover {
      transform: scale(0.95);
    }
  }
`;

export default function DeleteModal({ setDeleteModal }) {
  const navigate = useNavigate();

  const closeModal = () => {
    setDeleteModal(false);
    navigate('/');
  };

  const DeletePost = () => {
    axios
      .delete(`http://3.39.180.45:56178/DBtest/delete/7`, {
        headers: { Authorization: getLoginCookie() },
      })
      .then((res) => {
        console.log(res);
        console.log('삭제 성공');
        closeModal();
        navigate('/');
      })
      .catch((err) => console.log(err, 'Error'));
  };

  return (
    <ModalBackdrop onClick={closeModal}>
      <Container>
        <Top>
          <div className="title">글 삭제</div>
          <div className="checkmessage">정말로 삭제하시겠습니까?</div>
        </Top>
        <Bottom>
          <button className="cancel" onClick={closeModal}>
            취소
          </button>

          <button className="confirm" onClick={DeletePost}>
            확인
          </button>
        </Bottom>
      </Container>
    </ModalBackdrop>
  );
}
