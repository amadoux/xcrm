import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './enterprise.reducer';

export const Enterprise = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const enterpriseList = useAppSelector(state => state.enterprise.entities);
  const loading = useAppSelector(state => state.enterprise.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="enterprise-heading" data-cy="EnterpriseHeading">
        <Translate contentKey="xcrmApp.enterprise.home.title">Enterprises</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="xcrmApp.enterprise.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/enterprise/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="xcrmApp.enterprise.home.createLabel">Create new Enterprise</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {enterpriseList && enterpriseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="xcrmApp.enterprise.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('companyName')}>
                  <Translate contentKey="xcrmApp.enterprise.companyName">Company Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('companyName')} />
                </th>
                <th className="hand" onClick={sort('businessRegisterNumber')}>
                  <Translate contentKey="xcrmApp.enterprise.businessRegisterNumber">Business Register Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('businessRegisterNumber')} />
                </th>
                <th className="hand" onClick={sort('uniqueIdentificationNumber')}>
                  <Translate contentKey="xcrmApp.enterprise.uniqueIdentificationNumber">Unique Identification Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uniqueIdentificationNumber')} />
                </th>
                <th className="hand" onClick={sort('businessDomicile')}>
                  <Translate contentKey="xcrmApp.enterprise.businessDomicile">Business Domicile</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('businessDomicile')} />
                </th>
                <th className="hand" onClick={sort('businessEmail')}>
                  <Translate contentKey="xcrmApp.enterprise.businessEmail">Business Email</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('businessEmail')} />
                </th>
                <th className="hand" onClick={sort('businessPhone')}>
                  <Translate contentKey="xcrmApp.enterprise.businessPhone">Business Phone</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('businessPhone')} />
                </th>
                <th className="hand" onClick={sort('country')}>
                  <Translate contentKey="xcrmApp.enterprise.country">Country</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('country')} />
                </th>
                <th className="hand" onClick={sort('city')}>
                  <Translate contentKey="xcrmApp.enterprise.city">City</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('city')} />
                </th>
                <th className="hand" onClick={sort('businessLogo')}>
                  <Translate contentKey="xcrmApp.enterprise.businessLogo">Business Logo</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('businessLogo')} />
                </th>
                <th className="hand" onClick={sort('mapLocator')}>
                  <Translate contentKey="xcrmApp.enterprise.mapLocator">Map Locator</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mapLocator')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {enterpriseList.map((enterprise, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/enterprise/${enterprise.id}`} color="link" size="sm">
                      {enterprise.id}
                    </Button>
                  </td>
                  <td>{enterprise.companyName}</td>
                  <td>{enterprise.businessRegisterNumber}</td>
                  <td>{enterprise.uniqueIdentificationNumber}</td>
                  <td>{enterprise.businessDomicile}</td>
                  <td>{enterprise.businessEmail}</td>
                  <td>{enterprise.businessPhone}</td>
                  <td>
                    <Translate contentKey={`xcrmApp.Pays.${enterprise.country}`} />
                  </td>
                  <td>{enterprise.city}</td>
                  <td>
                    {enterprise.businessLogo ? (
                      <div>
                        {enterprise.businessLogoContentType ? (
                          <a onClick={openFile(enterprise.businessLogoContentType, enterprise.businessLogo)}>
                            <img
                              src={`data:${enterprise.businessLogoContentType};base64,${enterprise.businessLogo}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {enterprise.businessLogoContentType}, {byteSize(enterprise.businessLogo)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {enterprise.mapLocator ? (
                      <div>
                        {enterprise.mapLocatorContentType ? (
                          <a onClick={openFile(enterprise.mapLocatorContentType, enterprise.mapLocator)}>
                            <img
                              src={`data:${enterprise.mapLocatorContentType};base64,${enterprise.mapLocator}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {enterprise.mapLocatorContentType}, {byteSize(enterprise.mapLocator)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/enterprise/${enterprise.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/enterprise/${enterprise.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/enterprise/${enterprise.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="xcrmApp.enterprise.home.notFound">No Enterprises found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Enterprise;
