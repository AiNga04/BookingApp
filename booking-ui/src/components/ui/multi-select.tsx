import * as React from "react";
import { Check, ChevronDown } from "lucide-react";
import { cn } from "@/lib/utils";

export interface MultiSelectOption {
  label: string;
  value: string | number;
}

interface MultiSelectProps {
  options: MultiSelectOption[];
  value: (string | number)[];
  onChange: (value: (string | number)[]) => void;
  placeholder?: string;
  className?: string;
}

export const MultiSelect: React.FC<MultiSelectProps> = ({
  options,
  value,
  onChange,
  placeholder = "Chọn...",
  className = "",
}) => {
  const [open, setOpen] = React.useState(false);

  const handleSelect = (optionValue: string | number) => {
    if (value.includes(optionValue)) {
      onChange(value.filter((v) => v !== optionValue));
    } else {
      onChange([...value, optionValue]);
    }
  };

  return (
    <div className={cn("relative", className)}>
      <button
        type="button"
        className={cn(
          "flex w-full items-center justify-between rounded-md border-2 border-gray-300 bg-white px-3 py-2 text-left text-sm shadow-sm focus:border-[#ff8c42] focus:outline-none",
          open && "ring-2 ring-[#ff8c42]"
        )}
        onClick={() => setOpen((prev) => !prev)}
      >
        <span className={value.length === 0 ? "text-gray-400" : ""}>
          {value.length === 0
            ? placeholder
            : options
              .filter((opt) => value.includes(opt.value))
              .map((opt) => opt.label)
              .join(", ")}
        </span>
        <ChevronDown className="ml-2 h-4 w-4" />
      </button>
      {open && (
        <div className="absolute z-10 mt-1 w-full rounded-md bg-white border border-gray-200 shadow-lg max-h-60 overflow-auto">
          {options.map((opt) => (
            <div
              key={opt.value}
              className={cn(
                "flex cursor-pointer items-center px-3 py-2 hover:bg-[#fff5e6]",
                value.includes(opt.value) && "bg-[#ffecd3]"
              )}
              onClick={() => handleSelect(opt.value)}
            >
              <span className="mr-2 flex h-4 w-4 items-center justify-center border border-gray-300 rounded">
                {value.includes(opt.value) && <Check className="h-3 w-3 text-[#ff8c42]" />}
              </span>
              <span>{opt.label}</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};