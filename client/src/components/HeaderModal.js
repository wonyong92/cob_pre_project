import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import styled from 'styled-components';
import { faStackOverflow } from '@fortawesome/fontawesome-free-brands';
import { Link } from 'react-router-dom';
/* eslint-disable react/prop-types */

const ModalBack = styled.div`
  display: flex;
  justify-content: flex-end;
  width: 100%;
  height: 100%;
  position: absolute;
  right: 70px;
  top: 47px;
`;
const ModalView = styled.div`
  display: flex;
  flex-direction: column;
  width: 370px;
  height: 70px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 7px 0px;

  color: #0074cc;
  .titleBox {
    background-color: #f1f2f3;
  }
  .title {
    padding: 10px;
    font-weight: 700;
    font-size: 12px;
  }
  .wrapper {
    display: flex;
    justify-content: space-between;
    padding-bottom: 10px;
    background-color: #f8fbfd;
    button:first-child {
      background-color: transparent;
      border: none;
    }
  }
  .wrapper:hover {
    background-color: #e1ecf4;
  }
  a {
    color: #0074cc;
    text-decoration: none;
  }
  .logoWrapper {
    font-weight: 700;
    padding-top: 10px;
    padding-left: 15px;
    display: flex;
    justify-content: space-between;
    width: 130px;
    font-size: 14px;
  }
  .logo {
    color: black;
  }
  .btnWrapper {
    padding-top: 10px;
    padding-right: 15px;
    display: flex;
    justify-content: space-between;
    width: 130px;
    button {
      color: #0074cc;
      cursor: pointer;
      font-size: 14px;
      border: none;
      background-color: transparent;
    }
  }
`;

const HeaderModal = ({ viewModal, setModal }) => {
  const closeModal = (event) => {
    if (event.target.className.includes('ModalBack') && viewModal)
      setModal(false);
  };
  return (
    <ModalBack className="ModalBack" onClick={closeModal}>
      <ModalView>
        <div className="titleBox">
          <div className="title">CURRENT COMMUNITY</div>
        </div>
        <div className="wrapper">
          <button onClick={() => setModal(false)}>
            <Link to="/">
              <div className="logoWrapper">
                <FontAwesomeIcon icon={faStackOverflow} className="logo" />
                <div>Stack Overflow</div>
              </div>
            </Link>
          </button>
          <div className="btnWrapper">
            <button>help</button>
            <button>chat</button>
            <button onClick={() => setModal(false)}>
              <Link to="/logout">
                <span>logout</span>
              </Link>
            </button>
          </div>
        </div>
      </ModalView>
    </ModalBack>
  );
};

export default HeaderModal;
