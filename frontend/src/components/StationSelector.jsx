import React from "react";

const StationSelector = ({ stations, label, name, register, errors }) => {
  return (
    <div className="mb-3">
      <label className="form-label">{label}</label>
      <select
        className="form-select"
        {...register(name, { required: `${label} is required` })}
      >
        <option value="">Select a station</option>
        {stations.map((station) => (
          <option key={station.name} value={station.name}>
            {station.name}
          </option>
        ))}
      </select>
      {errors[name] && <p className="text-danger mt-1">{errors[name].message}</p>}
    </div>
  );
};

export default StationSelector;
